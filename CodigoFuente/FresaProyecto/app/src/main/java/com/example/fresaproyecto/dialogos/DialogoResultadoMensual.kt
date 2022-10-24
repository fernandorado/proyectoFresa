package com.example.fresaproyecto.dialogos

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.databinding.FragmentDialogoResultadoMensualBinding
import com.example.fresaproyecto.databinding.FragmentInicioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoResultadoMensual.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoResultadoMensual : DialogFragment() {

    private var _binding: FragmentDialogoResultadoMensualBinding?=null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menuoverflow, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogoResultadoMensualBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarRes)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id: Int = item.getItemId()
        if (id == R.id.opcEditar) {

            //El código que se ejecutara al hacer click en esa opción
        }else if (id == R.id.opcEliminar) {

        }
        return super.onOptionsItemSelected(item)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoResultadoMensual.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoResultadoMensual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}