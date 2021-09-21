package com.example.roomandapi.views

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.roomandapi.entity.TeamMember
import com.example.roomandapi.sampledata.teamMemberList
import com.example.roomandapi.viewmodel.TeamMemberViewModel
import com.example.roomandapi.viewmodel.TeamMemberViewModelFactory

@Composable
fun AboutView (navController: NavController){
    val context = LocalContext.current
    val teamMemberViewModel: TeamMemberViewModel = viewModel(
        factory = TeamMemberViewModelFactory(context.applicationContext as Application)
    )
    teamMemberViewModel.addTeamMember(teamMemberList)

    val teamList = teamMemberViewModel.readAllTeamMembers.observeAsState(listOf()).value
    Column(){
        Button(onClick = { navController.navigate("main") }) {
            Text("Main Menu")
        }
        Spacer(Modifier.padding(20.dp))
        Text("Team Members")
        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ){
            items(teamList.size){ index->
                TeamMemberItem(teamList[index])
            }
        }

    }

}

@Composable
fun TeamMemberItem(teamMember: TeamMember) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(){
                    Image(
                        painter = rememberImagePainter(teamMember.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )
                    Text(teamMember.bio)
                }

                Text(
                    text = " ${teamMember.firstName} ${teamMember.lastName}",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
    }
}