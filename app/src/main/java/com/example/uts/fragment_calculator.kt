package com.example.uts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.uts.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var expression: Expression

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_calculator.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_calculator : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCalculatorBinding
    private var lastNumeric = false
    private var stateError = false
    private var lastDot = false
    private lateinit var expression: Expression


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        // Inisialisasi expression
        expression = ExpressionBuilder("0").build()

        // Tambahkan event click ke tombol-tombol di sini
        binding.btn1.setOnClickListener { onDigitClick(binding.btn1) }
        binding.btn2.setOnClickListener { onDigitClick(binding.btn2) }
        binding.btn3.setOnClickListener { onDigitClick(binding.btn3) }
        binding.btn4.setOnClickListener { onDigitClick(binding.btn4) }
        binding.btn5.setOnClickListener { onDigitClick(binding.btn5) }
        binding.btn6.setOnClickListener { onDigitClick(binding.btn6) }
        binding.btn7.setOnClickListener { onDigitClick(binding.btn7) }
        binding.btn8.setOnClickListener { onDigitClick(binding.btn8) }
        binding.btn9.setOnClickListener { onDigitClick(binding.btn9) }
        binding.btn0.setOnClickListener { onDigitClick(binding.btn0) }
        binding.btn10.setOnClickListener { onDigitClick(binding.btn10) }

        binding.btnPlus.setOnClickListener { onOperatorClick(binding.btnPlus) }
        binding.btnMin.setOnClickListener { onOperatorClick(binding.btnMin) }
        binding.btnMul.setOnClickListener { onOperatorClick(binding.btnMul) }
        binding.btnDiv.setOnClickListener { onOperatorClick(binding.btnDiv) }

        binding.btnAc.setOnClickListener { onAllClearClick() }
        binding.btnBack.setOnClickListener { onBackClick() }
        binding.btnPercent.setOnClickListener { onOperatorClick(binding.btnPercent) }

        binding.btnEqual.setOnClickListener { onEqualClick() }
        binding.btnDot.setOnClickListener { onDigitClick(binding.btnDot) }

        return binding.root
    }

    private fun onAllClearClick() {
        binding.dataTv.text = ""
        binding.resultTv.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        binding.resultTv.visibility = View.GONE
    }

    private fun onEqualClick() {
        onEqual()
        binding.dataTv.text = binding.resultTv.text.toString().drop(1)
    }

    private fun onDigitClick(view: View) {
        if (stateError) {
            binding.dataTv.text = (view as Button).text
            stateError = false
        } else {
            binding.dataTv.append((view as Button).text)
        }
        lastNumeric = true
    }

    private fun onOperatorClick(view: View) {
        if (!stateError && lastNumeric) {
            binding.dataTv.append((view as Button).text)
            lastDot = false
            lastNumeric = false
            onEqual()
        }
    }

    private fun onBackClick() {
        binding.dataTv.text = binding.dataTv.text.toString().dropLast(1)
        try {
            val lastChar = binding.dataTv.text.toString().last()
            if (lastChar.isDigit()) {
                onEqual()
            }
        } catch (e: Exception) {
            binding.resultTv.text = ""
            binding.resultTv.visibility = View.GONE
            Log.e("last char error", e.toString())
        }
    }

    private fun onEqual() {
        if (lastNumeric && !stateError) {
            val txt = binding.dataTv.text.toString().trim()

            if (txt.isNotEmpty()) {
                expression = ExpressionBuilder(txt).build()

                try {
                    val result = expression.evaluate()
                    binding.resultTv.visibility = View.VISIBLE
                    binding.resultTv.text = "=$result"
                } catch (ex: ArithmeticException) {
                    Log.e("evaluate error", ex.toString())
                    binding.resultTv.text = "Error"
                    stateError = true
                    lastNumeric = true
                }
            } else {
                binding.resultTv.text = ""
                binding.resultTv.visibility = View.GONE
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_calculator.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_calculator().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}