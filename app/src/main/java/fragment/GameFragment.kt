package fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newgame.databinding.FragmentGameBinding
import data.DataExercise

private val data = DataExercise()
private var bool = false
private const val PLAYER1 = "Игрок 1"
private const val PLAYER2 = "Игрок 2"
private var exercise = data.femaleExercise.random()


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            startGame()
            binding.txtExercise.text = exercise
        }
        binding.btnNextPlayer.setOnClickListener {
            nextPlayer()
            nextExercise()
        }
        binding.btnOtherExercise.setOnClickListener {
            nextExercise()
        }
    }

    private fun startGame() {
        with(binding) {
            btnStart.gone()
            btnNextPlayer.show()
            btnOtherExercise.show()
            txtPlayer.text = PLAYER1
        }
    }

    private fun nextPlayer() {
        if (bool) {
            binding.txtPlayer.text = PLAYER2
            bool = !bool
        } else {
            binding.txtPlayer.text = PLAYER1
            bool = !bool
        }
    }

    private fun nextExercise() {
            if (bool) {
                binding.txtExercise.text = data.femaleExercise.random()
            } else
                binding.txtExercise.text = data.maleExercise.random()
    }


    companion object {
        fun newInstance() = GameFragment()
    }

    private fun View.show() {
        this.visibility = View.VISIBLE
    }

    private fun View.gone() {
        this.visibility = View.GONE
    }
}
