package ec.edu.espoch.appfragmento

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val listViewUsuarios = view.findViewById<ListView>(R.id.listViewUsuarios)
        val usuarios = obtenerUsuarios()
        // Usar un ArrayAdapter simple
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, usuarios)
        listViewUsuarios.adapter = adapter
        return view
    }

    private fun obtenerUsuarios(): List<String> {
        val admin = ConexionSql(requireContext(), "dbSistema", null, 1)
        val bd = admin.readableDatabase
        val cursor: Cursor = bd.rawQuery("SELECT nombre FROM usuario", null)
        val usuarios = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                usuarios.add(nombre)
            } while (cursor.moveToNext())
        }
        cursor.close()
        bd.close()
        return usuarios
    }
}