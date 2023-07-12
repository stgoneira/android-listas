package cl.stgoneira.learning.android.listas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoListView2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoListView2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val rootView = inflater.inflate(R.layout.fragmento_list_view2, container, false)
        val listView = rootView.findViewById<ListView>(R.id.listView2)

        val comunas         = mutableListOf<Map<String, String>>()
        val comunasArr = resources.getStringArray(R.array.comunas2)
        comunasArr.forEach {
            val comunaRegionArr = it.split("|")
            val comuna = comunaRegionArr[0]
            val region = comunaRegionArr[1]
            comunas.add( mapOf("comuna" to comuna, "region" to region) )
        }

        val from            = arrayOf("comuna", "region")
        val to              = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter         = SimpleAdapter(requireContext(), comunas, android.R.layout.simple_list_item_2, from, to)
        listView.adapter    = adapter
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoListView2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoListView2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}