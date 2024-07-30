package ec.edu.espoch.appfragmento

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView

class VideoFragment : Fragment() {

    private val YOUTUBE_VIDEO_ID = "67tTogkJatg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_video, container, false)
        val webView = rootView.findViewById<WebView>(R.id.youtube_webview)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        webView.webViewClient = WebViewClient()
        val iframeHtml = """
            <html>
            <body>
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/$YOUTUBE_VIDEO_ID" frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </body>
            </html>
        """
        webView.loadData(iframeHtml, "text/html", "utf-8")

        return rootView
    }
}