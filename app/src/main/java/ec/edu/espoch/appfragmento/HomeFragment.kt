package ec.edu.espoch.appfragmento

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class HomeFragment : Fragment() {
    /*
    declare variables
    * */
    private lateinit var userName: EditText
    private lateinit var userSexGroup: RadioGroup
    private lateinit var transportCar: CheckBox
    private lateinit var transportMotorCycle: CheckBox
    private lateinit var transportTriCycle: CheckBox
    private lateinit var ageRange: Spinner
    private lateinit var showInformation: TextView

    private lateinit var addUser: Button


   // private lateinit var label_show_data: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userName= view.findViewById(R.id.user_name)
        userSexGroup = view.findViewById(R.id.radioGroup_sexUser)
        transportCar= view.findViewById(R.id.checkbox_car)
        transportMotorCycle= view.findViewById(R.id.checkbox_motorcycle)
        transportTriCycle= view.findViewById(R.id.checkbox_tricycle)
        ageRange=view.findViewById(R.id.spinner_ageRange)
        showInformation=view.findViewById(R.id.label_showInformation)
        addUser=view.findViewById(R.id.button_submit)


        //label_show_data = view.findViewById(R.id.label_showName)


        /* EditText*/
        addUser.setOnClickListener {
            val dataEditText = userName.text.toString()
            //showInformation.text= dataEditText
            //label_show_data.text=" $data_text"
            ingresarUsuario(dataEditText)

            Log.d("Information-data", "edit-Text: $dataEditText")
        }

        /*RadioButton*/
        userSexGroup.setOnCheckedChangeListener { group, checkedId ->

            val radioButton: RadioButton = view.findViewById(checkedId)
            val selectedOption = radioButton.text.toString()
            Log.d("Information-data", "radio-Button: $selectedOption")
        }

        /*CheckBox*/
        transportCar.setOnCheckedChangeListener { buttonView, isChecked ->
            val car = isChecked
            showCheckBoxValues(car, transportMotorCycle.isChecked, transportTriCycle.isChecked)
        }
        transportMotorCycle.setOnCheckedChangeListener { buttonView, isChecked ->
            val motoCycle = isChecked
            showCheckBoxValues(transportCar.isChecked, motoCycle, transportTriCycle.isChecked)
        }
        transportTriCycle.setOnCheckedChangeListener { buttonView, isChecked ->
            val tricycle = isChecked
            showCheckBoxValues(transportCar.isChecked, transportMotorCycle.isChecked,tricycle)
        }

       /* Spinner*/
        ageRange = view.findViewById(R.id.spinner_ageRange)
        val elementosSpinner = resources.getStringArray(R.array.array_ageRange)
        // Adaptador para el Spinner
        val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, elementosSpinner)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Asignar el adaptador al Spinner
        ageRange.adapter = adaptador

        // Agregar un escuchador al Spinner
        ageRange.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Obtener el valor seleccionado
                val valorSeleccionado = elementosSpinner[position]

                // Imprimir el valor en la consola
                Log.d("Information-data", "Spiner: $valorSeleccionado")
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Acciones a realizar cuando no hay nada seleccionado (opcional)
            }
        }
    }

    private fun showCheckBoxValues(car: Boolean, motorcycle: Boolean, tricycle: Boolean) {
            Log.d("Information-data", "CheckBox: carro-$car, moto-$motorcycle, triciclo-$tricycle")

    }

    private fun ingresarUsuario(nombre: String) {
        val admin = ConexionSql(requireContext(), "dbSistema", null, 1)
        val bd = admin.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
        }
        val newRowId = bd.insert("usuario", null, values)
        if (newRowId != -1L) {
            Toast.makeText(requireContext(), "Datos guardados con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show()
        }
        //bd.close()
    }

}