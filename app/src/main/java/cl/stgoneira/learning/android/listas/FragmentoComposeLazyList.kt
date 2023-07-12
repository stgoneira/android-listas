package cl.stgoneira.learning.android.listas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController

class FragmentoComposeLazyList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val comunasArr  = resources.getStringArray(R.array.comunas2)
        val comunas     = mutableListOf<Comuna>()
        comunasArr.forEach {
            val comunaRegionArr = it.split("|")
            val comuna = comunaRegionArr[0]
            val region = comunaRegionArr[1]
            comunas.add( Comuna(comuna, region, null) )
        }
        return ComposeView( requireContext() ).apply {
            setViewCompositionStrategy( ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed )
            setContent {
                ListaComunas(comunas)
            }
        }
    }
}

@Composable
fun ListaComunas(comunas: List<Comuna>) {
    val contexto        = LocalContext.current
    val navController   = LocalView.current.findNavController()

    LazyColumn {
        items(comunas) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 0.5.dp,
                        color = Color.LightGray
                    )
                    .padding(all = 10.dp)
                    .clickable {
                        Toast
                            .makeText(contexto, "Comuna: ${it.comuna}", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(R.id.action_fragmentoComposeLazyList_to_fragmentoInicio)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.flag_of_chile),
                    contentDescription = "Bandera de Chile",
                    modifier = Modifier
                        .width(100.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(text = it.comuna, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                    Text(it.region)
                }
            }            
        }
    }
}

@Preview
@Composable
fun Preview() {
    val comunas = mutableListOf<Comuna>()
    comunas.add( Comuna("Lo Prado", "Región Metropolitana", null) )
    comunas.add( Comuna("Vitacura", "Región Metropolitana", null) )
    ListaComunas(comunas)
}