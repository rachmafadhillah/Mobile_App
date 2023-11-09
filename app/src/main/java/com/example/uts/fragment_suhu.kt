package com.example.uts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.ArrayAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_suhu.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_suhu : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null
    private var edit1: EditText? = null
    private var edit2: EditText? = null
    private var spinner1: Spinner? = null
    private var spinner2: Spinner? = null
    private var hitungButton: Button? = null
    private var layoutFormula: View? = null
    private var textFormula: TextView? = null

    private val temperatures = arrayOf(
        "\u00B0C",
        "\u00B0R",
        "\u00B0F",
        "K"
    )

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_suhu, container, false)

        edit1 = view.findViewById(R.id.edit1)
        edit2 = view.findViewById(R.id.edit2)
        spinner1 = view.findViewById(R.id.spinner1)
        spinner2 = view.findViewById(R.id.spinner2)
        hitungButton = view.findViewById(R.id.hitung)
        layoutFormula = view.findViewById(R.id.layout_formula)
        textFormula = view.findViewById(R.id.text_formula)

        // Inisialisasi adapter untuk Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, temperatures)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Atur adapter ke Spinner
        spinner1?.adapter = adapter
        spinner2?.adapter = adapter

        hitungButton?.setOnClickListener {
            hitungKonversiSuhu()
        }

        // Set up any click listeners or functionality for your UI elements here

        return view
    }

    private fun hitungKonversiSuhu() {
        val inputSuhu = edit1?.text.toString().toDouble()
        val suhuAwal = spinner1?.selectedItem.toString()
        val suhuTujuan = spinner2?.selectedItem.toString()
        var hasilKonversi: Double = 0.0
        var formula = ""

        when {
            //celcius -> F
            suhuAwal == temperatures[0] && suhuTujuan == temperatures[2] -> {
                hasilKonversi = (inputSuhu * 9/5) + 32
                formula = "Fahrenheit = ((Celsius × 9/5) + 32)"
            }
            // F -> C
            suhuAwal == temperatures[2] && suhuTujuan == temperatures[0] -> {
                hasilKonversi = (inputSuhu - 32) * 5/9
                formula = "Celsius = ((Fahrenheit - 32) × 5/9)"
            }
            //C -> K
            suhuAwal == temperatures[0] && suhuTujuan == temperatures[3] -> {
                hasilKonversi = inputSuhu + 273.15
                formula = "Kelvin = (Celsius + 273.15)"
            }
            //K -> C
            suhuAwal == temperatures[3] && suhuTujuan == temperatures[0] -> {
                hasilKonversi = inputSuhu - 273.15
                formula = "Celsius = (Kelvin - 273.15)"
            }
            // R -> C
            suhuAwal == temperatures[1] && suhuTujuan == temperatures[0] -> {
                hasilKonversi = (inputSuhu * 5/4)
                formula = "Celsius = (Reamur × 5/4)"
            }
            //C -> R
            suhuAwal == temperatures[0] && suhuTujuan == temperatures[1] -> {
                hasilKonversi = (inputSuhu * 4/5)
                formula = "Reamur = (Celsius × 4/5)"
            }
            // Fahrenheit to Kelvin
            suhuAwal == temperatures[2] && suhuTujuan == temperatures[3] -> {
                hasilKonversi = (inputSuhu - 32) * 5/9 + 273.15
                formula = "Kelvin = ((Fahrenheit - 32) × 5/9 + 273.15)"
            }
            // Fahrenheit to Reamur
            suhuAwal == temperatures[2] && suhuTujuan == temperatures[1] -> {
                hasilKonversi = ((inputSuhu - 32) * 4/9)
                formula = "Reamur = ((Fahrenheit - 32) × 4/9)"
            }
            // Reamur to Fahrenheit
            suhuAwal == temperatures[1] && suhuTujuan == temperatures[2] -> {
                hasilKonversi = (inputSuhu * 9/4) + 32
                formula = "Fahrenheit = ((Reamur × 9/4) + 32)"
            }
            // Reamur to Kelvin
            suhuAwal == temperatures[1] && suhuTujuan == temperatures[3] -> {
                hasilKonversi = (inputSuhu * 5/4) + 273.15
                formula = "Kelvin = ((Reamur × 5/4) + 273.15)"
            }
            // Kelvin to Reamur
            suhuAwal == temperatures[3] && suhuTujuan == temperatures[1] -> {
                hasilKonversi = (inputSuhu - 273.15) * 4/5
                formula = "Reamur = ((Kelvin - 273.15) × 4/5)"
            }
            // Kelvin to Fahrenheit
            suhuAwal == temperatures[3] && suhuTujuan == temperatures[2] -> {
                hasilKonversi = (inputSuhu - 273.15) * 9/5 + 32
                formula = "Fahrenheit = ((Kelvin - 273.15) × 9/5 + 32)"
            }
        }

        edit2?.setText(hasilKonversi.toString())
        textFormula?.text = "RUMUS : $formula"
        layoutFormula?.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_suhu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_suhu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}