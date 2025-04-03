package pe.edu.upeu.calcuupeupc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.calcuupeupc.ui.theme.CalcuUPEUPCTheme
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            calculadoraGUI()
        }
    }
}

@Preview
@Composable
fun calculadoraGUI() {
    var valorA by remember { mutableStateOf("") }
    var valorO by remember { mutableStateOf("") }
    var oper by remember { mutableStateOf("") }

    CalcuUPEUPCTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = valorA, fontSize = 30.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))

            val button = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("C", "0", "=", "+"),
                listOf("π", "sqrt", "1/x", "mod"),
                listOf("x^y")
            )

            button.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { b ->
                        Button(onClick = {
                            when (b) {
                                "=" -> {
                                    try {
                                        when (oper) {
                                            "+" -> {
                                                valorA = (valorO.toDouble() + valorA.toDouble()).toString()
                                            }
                                            "-" -> {
                                                valorA = (valorO.toDouble() - valorA.toDouble()).toString()
                                            }
                                            "*" -> {
                                                valorA = (valorO.toDouble() * valorA.toDouble()).toString()
                                            }
                                            "/" -> {
                                                valorA = (valorO.toDouble() / valorA.toDouble()).toString()
                                            }
                                            "x^y" -> {
                                                valorA = (valorO.toDouble().pow(valorA.toDouble())).toString()
                                            }
                                        }
                                        oper = ""
                                        valorO = ""
                                    } catch (e: Exception) {
                                        valorA = "Error"
                                    }
                                }
                                "+", "-", "*", "/" -> {
                                    oper = b
                                    valorO = valorA
                                    valorA = ""
                                }
                                "C" -> {
                                    valorA = ""
                                    valorO = ""
                                    oper = ""
                                }
                                "π" -> {
                                    valorA = PI.toString()
                                }
                                "sqrt" -> {
                                    try {
                                        valorA = sqrt(valorA.toDouble()).toString()
                                    } catch (e: Exception) {
                                        valorA = "Error"
                                    }
                                }
                                "1/x" -> {
                                    try {
                                        valorA = (1 / valorA.toDouble()).toString()
                                    } catch (e: Exception) {
                                        valorA = "Error"
                                    }
                                }
                                "mod" -> {
                                    try {
                                        valorA = abs(valorA.toDouble()).toString()
                                    } catch (e: Exception) {
                                        valorA = "Error"
                                    }
                                }
                                "x^y" -> {
                                    oper = "x^y"
                                    valorO = valorA
                                    valorA = ""
                                }
                                else -> {
                                    valorA += b
                                }
                            }
                        }) {
                            Text(text = b, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalcuUPEUPCTheme {
        Greeting("Android")
    }
}
