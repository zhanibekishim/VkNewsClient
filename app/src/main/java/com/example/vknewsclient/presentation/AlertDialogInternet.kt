package com.example.vknewsclient.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vknewsclient.ui.theme.design.BEIGE
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogAbsenceInternet(){
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        BasicAlertDialog(
            onDismissRequest = { openDialog.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                color = BEIGE,
                contentColor = Color.Red
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text =
                        "Please turn on the internet and restart the application",
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextButton(
                        onClick = { openDialog.value = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        TextButton( onClick = {
                            openDialog.value = false
                            exitProcess(0) }
                        ) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }
}