package reprator.arrk.utility.retrofit2;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import timber.log.Timber;

//https://gist.github.com/erlangp/55ae6494fc37c2ee1a0a3a4478eef2ca
public final class NetworkUtilsAcceptSelfSignedSslCert {

    final private String TAG = "Tag.NetworkUtilsAcceptSelfSignedSslCert";
    final private String HOST;

    @Inject
    public NetworkUtilsAcceptSelfSignedSslCert(String host)
    {
        //HOST = BuildConfig.HOST;
        HOST = host;
    }

    public OkHttpClient setSelfSignedSSL(OkHttpClient.Builder okHttpClientBuilder) {

        final TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
               Timber.i(TAG + ": authType: " + String.valueOf(authType));
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
                Timber.i(TAG + ": authType: " + String.valueOf(authType));
            }
        }};

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
                Timber.i(TAG + ": authType: " + String.valueOf(authType));
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) throws CertificateException {
                Timber.i(TAG + ": authType: " + String.valueOf(authType));
            }
        };

        try {
            final String PROTOCOL = "SSL";
            SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
            KeyManager[] keyManagers = null;
            SecureRandom secureRandom = new SecureRandom();
            sslContext.init(keyManagers, trustManagers, secureRandom);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClientBuilder.sslSocketFactory(sslSocketFactory, x509TrustManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                Timber.i(TAG + ": hostname: " + String.valueOf(hostname));
                return hostname.equals(HOST);
            }
        };

        okHttpClientBuilder.hostnameVerifier(hostnameVerifier);
        return okHttpClientBuilder.build();
    }
}
