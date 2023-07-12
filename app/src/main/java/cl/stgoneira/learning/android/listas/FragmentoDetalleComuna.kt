package cl.stgoneira.learning.android.listas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoDetalleComuna.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoDetalleComuna : Fragment() {
    private var comuna: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comuna = it.getString("comuna")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragmento_detalle_comuna, container, false)
        val tvDetalleComuna = rootView.findViewById<TextView>(R.id.tvDetalleComuna)
        val tvDetalleRegion = rootView.findViewById<TextView>(R.id.tvDetalleRegion)
        tvDetalleComuna.text = comuna
        val comunasResArr   = resources.getStringArray(R.array.comunas2)
        comunasResArr.forEach {
            val arr = it.split("|")
            if( arr[0] == comuna) {
                tvDetalleRegion.text = arr[1]
                return@forEach
            }
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoDetalleComuna.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoDetalleComuna().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}