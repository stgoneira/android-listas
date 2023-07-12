package cl.stgoneira.learning.android.listas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class FragmentoRecyclerView : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragmento_recycler_view, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val comunasArr = resources.getStringArray(R.array.comunas2)
        val comunas = comunasArr.map {
            val arr = it.split("|")
            Comuna(arr.get(0), arr.get(1), null)
        }
        recyclerView.adapter = AdaptadorRecyclerView(comunas)

        return rootView
    }
}

data class Comuna(val comuna:String, val region:String, val imagen:String?)

class AdaptadorRecyclerView(private val comunas: List<Comuna>) : RecyclerView.Adapter<AdaptadorRecyclerView.ViewHolder>() {

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val ivComuna:ImageView
        val tvComuna:TextView
        val tvRegion:TextView

        init {
            ivComuna = view.findViewById(R.id.ivImagenComuna)
            tvComuna = view.findViewById(R.id.tvComuna)
            tvRegion = view.findViewById(R.id.tvRegion)
        }
    }

    override fun getItemCount(): Int = comunas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_comuna_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvComuna.text = comunas.get(position).comuna
        viewHolder.tvRegion.text = comunas.get(position).region
        viewHolder.ivComuna.setImageResource(R.drawable.flag_of_chile)
    }

}
