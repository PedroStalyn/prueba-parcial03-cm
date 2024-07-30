package ec.edu.espoch.appfragmento

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class MusicFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_music, container, false)

        val playButton = rootView.findViewById<Button>(R.id.button_play)
        val pauseButton = rootView.findViewById<Button>(R.id.button_pause)
        val stopButton = rootView.findViewById<Button>(R.id.button_stop)

        playButton.setOnClickListener {
            playMusic()
        }

        pauseButton.setOnClickListener {
            pauseMusic()
        }

        stopButton.setOnClickListener {
            stopMusic()
        }

        return rootView
    }
    private fun playMusic() {
        if (!this::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.song)
            mediaPlayer.start()
        } else if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseMusic() {
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun stopMusic() {
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            //mediaPlayer.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }

}