package app.moviebase.androidx.customtab;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import java.util.List;

import javax.inject.Inject;


public class CustomTabActivityHelper implements ServiceConnectionCallback {

  private CustomTabsSession mCustomTabsSession;
  private CustomTabsClient mClient;
  private CustomTabsServiceConnection mConnection;
  private ConnectionCallback mConnectionCallback;

  @Inject
  public CustomTabActivityHelper() {
  }

  public static void openCustomTab(Context context,
                                   Uri uri,
                                   Runnable fallback,
                                   @Nullable CustomTabsSession session,
                                   @ColorInt int color) {
    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(session);
    builder.setShowTitle(true);
    builder.setToolbarColor(color);
    CustomTabsIntent intent = builder.build();
    // intent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    intent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    CustomTabActivityHelper.openCustomTab(context, intent, uri, fallback);
  }

  /**
   * Opens the URL on a Custom Tab if possible. Otherwise fallsback e. g. to opening it on a WebView.
   *
   * @param context          The host activity.
   * @param customTabsIntent a CustomTabsIntent to be used if Custom Tabs is available.
   * @param uri              the Uri to be opened.
   * @param fallback         a CustomTabFallback to be used if Custom Tabs is not available.
   */
  public static void openCustomTab(Context context,
                                   CustomTabsIntent customTabsIntent,
                                   Uri uri,
                                   Runnable fallback) {
    String packageName = CustomTabsHelper.getPackageNameToUse(context);

    //If we cant find a package name, it means theres no browser that supports
    //Chrome Custom Tabs installed. So, we fallback to the webview
    if (packageName == null) {
      if (fallback != null) {
        fallback.run();
      }
    }
    else {
      try {
        customTabsIntent.intent.setPackage(packageName);
        customTabsIntent.launchUrl(context, uri);
      }
      catch (ActivityNotFoundException e) {
        if (fallback != null) {
          fallback.run();
        }
      }
    }
  }

  /**
   * Unbinds the Activity from the Custom Tabs Service.
   *
   * @param activity the activity that is connected to the service.
   */
  public void unbindCustomTabsService(Activity activity) {
    if (mConnection == null) {
      return;
    }
    activity.unbindService(mConnection);
    mClient = null;
    mCustomTabsSession = null;
    mConnection = null;
  }

  /**
   * Creates or retrieves an exiting CustomTabsSession.
   *
   * @return a CustomTabsSession.
   */
  public CustomTabsSession getSession() {
    if (mClient == null) {
      mCustomTabsSession = null;
    }
    else if (mCustomTabsSession == null) {
      mCustomTabsSession = mClient.newSession(null);
    }
    return mCustomTabsSession;
  }

  /**
   * Register a Callback to be called when connected or disconnected from the Custom Tabs Service.
   *
   * @param connectionCallback
   */
  public void setConnectionCallback(ConnectionCallback connectionCallback) {
    this.mConnectionCallback = connectionCallback;
  }

  /**
   * Binds the Activity to the Custom Tabs Service.
   *
   * @param activity the activity to be binded to the service.
   */
  public void bindCustomTabsService(Activity activity) {
    if (mClient != null) {
      return;
    }

    String packageName = CustomTabsHelper.getPackageNameToUse(activity);
    if (packageName == null) {
      return;
    }

    mConnection = new ServiceConnection(this);
    CustomTabsClient.bindCustomTabsService(activity, packageName, mConnection);
  }

  public boolean hasClient() {
    return mClient != null;
  }

  /**
   * @return true if call to mayLaunchUrl was accepted.
   * @see {@link CustomTabsSession#mayLaunchUrl(Uri, Bundle, List)}.
   */
  public boolean mayLaunchUrl(Uri uri, Bundle extras, List<Bundle> otherLikelyBundles) {
    if (mClient == null) {
      return false;
    }

    CustomTabsSession session = getSession();
    if (session == null) {
      return false;
    }

    return session.mayLaunchUrl(uri, extras, otherLikelyBundles);
  }

  @Override
  public void onServiceConnected(CustomTabsClient client) {
    mClient = client;
    mClient.warmup(0L);  // This prevents backgrounding after redirection
    if (mConnectionCallback != null) {
      mConnectionCallback.onCustomTabsConnected();
    }
  }

  @Override
  public void onServiceDisconnected() {
    mClient = null;
    mCustomTabsSession = null;
    if (mConnectionCallback != null) {
      mConnectionCallback.onCustomTabsDisconnected();
    }
  }

  /**
   * A Callback for when the service is connected or disconnected. Use those callbacks to
   * handle UI changes when the service is connected or disconnected.
   */
  public interface ConnectionCallback {
    /**
     * Called when the service is connected.
     */
    void onCustomTabsConnected();

    /**
     * Called when the service is disconnected.
     */
    void onCustomTabsDisconnected();
  }

  /**
   * To be used as a fallback to open the Uri when Custom Tabs is not available.
   */
  public interface CustomTabFallback {
    /**
     * @param activity The Activity that wants to open the Uri.
     * @param uri      The uri to be opened by the fallback.
     */
    void openUri(Activity activity, Uri uri);
  }

}
