package com.example.roomandapi.views


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomandapi.entity.ProjectEntity
import com.example.roomandapi.sampledata.teamMemberList
import com.example.roomandapi.viewmodel.ProjectViewModel
import com.example.roomandapi.viewmodel.ProjectViewModelFactory
import com.example.roomandapi.viewmodel.TeamMemberViewModel
import com.example.roomandapi.viewmodel.TeamMemberViewModelFactory
import com.example.workout_companion.utility.DateTimeConverter
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddProjectView(navController: NavController) {
    val context = LocalContext.current
    val projectViewModel: ProjectViewModel = viewModel(
        factory = ProjectViewModelFactory(context.applicationContext as Application)
    )
    //remember the values in the form
    var projectName by remember { mutableStateOf("") }
    val projectTypeState = remember { mutableStateOf("")}
    val radioOptions = listOf("Easy", "Intermediate", "Difficult")
    val difficulty = remember { mutableStateOf("") }
    val dateValue = remember { mutableStateOf("") }
    val UserID = remember { mutableStateOf(0)}
    val UserIDString = remember { mutableStateOf("")}

    Column(
        Modifier.fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        //Store some navigation buttons
        Row(){
            Button(onClick = { navController.navigate("main") }) {
                Text("Main")
            }
            Button(onClick = { navController.navigate("about") }) {
                Text("About Us")
            }
            Button(onClick = { navController.navigate("addProject") }) {
                Text("Add a Project")
            }
        }
        //set a top margin
        Spacer(modifier = Modifier.padding(top = 30.dp))
        //Create a heading
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Text(text = "Add a Project")
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))

        //Form
        //Project Name Row
        // row has padding to the left and right margins
        Row() {
            Text(text = "Name of Project:")
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = projectName,
                onValueChange = { projectName = it }
            )
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))

        //Project Difficulty Radiobuttons
        // row has padding to the left and right margins
        Text("Project Difficulty: ")
        Row(
            Modifier.fillMaxWidth()
        ) {
            GroupedRadioButton(radioOptions, difficulty)
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))

        //Project Type Dropdown Row
        // row has padding to the left and right margins
        Row() {
            Text(text = "Project Type:")
            Spacer(modifier = Modifier.padding(10.dp))
            ProjectTypeDropdown(projectTypeState)
        }
        //Date Time Picker
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row() {
            Text(text = "Creation Date:")
            Spacer(modifier = Modifier.padding(10.dp))
            showDatePicker(dateValue)
        }
        //User Dropdown
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row() {
            Text(text = "User ID:")
            Spacer(modifier = Modifier.padding(10.dp))
            UserDropdown(UserID)
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))

        //Submit Button
        Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 120.dp, end = 120.dp)) {
            Button(onClick = { submit(projectName, difficulty.value,
                                     projectTypeState.value, dateValue.value,
                                     UserID.value, projectViewModel) }){
                Text("Submit")
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        // An approach for checking to see if state is being handled correctly
        //just a bunch of text fields with the value set to a particular state and
        // an onValueChange function that updates the value when the state changes
//        Row() {
//            Text("Current Project Name: ")
//            TextField(
//                value = projectName,
//                onValueChange = { projectName = it },)
//            Spacer(modifier = Modifier.padding(10.dp))
//        }
//        Row() {
//            Text("Current Project Difficulty: ")
//            TextField(
//                value = difficulty.value,
//                onValueChange = { difficulty.value = it },)
//            Spacer(modifier = Modifier.padding(10.dp))
//        }
//        Row() {
//            Text("Current Project Type: ")
//            TextField(
//                value = projectTypeState.value,
//                onValueChange = { projectTypeState.value = it },)
//            Spacer(modifier = Modifier.padding(10.dp))
//        }
//        Row() {
//            Text("Current Date: ")
//            TextField(
//                value = dateValue.value,
//                onValueChange = { dateValue.value = it },)
//            Spacer(modifier = Modifier.padding(10.dp))
//        }
    }
}

fun submit(projectName: String, difficulty: String,
           projectType:String, dateValue:String, UserID: Int, projectViewModel: ProjectViewModel ){

    val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    val date: LocalDate = LocalDate.parse(dateValue, formatter)
    var project = ProjectEntity(1, projectName, difficulty, projectType, date, UserID)
    projectViewModel.addProject(project)
}

@Composable
fun ProjectTypeDropdown(projectTypeState: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Personal Project", "School Project", "Work Project")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex],modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .background(
                Color.LightGray
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.LightGray
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    projectTypeState.value = items[selectedIndex]
                }) {
                    Text(text = s)
                }
            }
        }
    }
}


@Composable
fun GroupedRadioButton(mItems: List<String>, radioState: MutableState<String>) {

    Row(Modifier
        .fillMaxWidth()) {
        mItems.forEach { mItem ->
                RadioButton(
                    selected = radioState.value == mItem,
                    onClick = { radioState.value = mItem },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Magenta)
                )
            Text(text = mItem, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun ReadonlyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: @Composable () -> Unit
) {
    Box {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            label = label
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}


@Composable
fun showDatePicker(DateValue: MutableState<String>){

    val dialogState = rememberMaterialDialogState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val dialog = MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }){
        datepicker { date ->
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            val formattedDate = date.format(formatter)
            textState.value = TextFieldValue(formattedDate)
            DateValue.value = formattedDate
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ReadonlyTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            onClick = {
                dialogState.show()
            },

            label = {
                Text(text = "Date")
            }
        )
    }
}

@Composable
fun UserDropdown(UserID: MutableState<Int>) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val teamMemberViewModel: TeamMemberViewModel = viewModel(
        factory = TeamMemberViewModelFactory(context.applicationContext as Application)
    )
    teamMemberViewModel.addTeamMember(teamMemberList)
    val teamList = teamMemberViewModel.readAllTeamMembers.observeAsState(listOf()).value
    val items = listOf("Personal Project", "School Project", "Work Project")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        if(teamList.isNotEmpty()) {
            Text(
                "${teamList[selectedIndex].id}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(
                        Color.LightGray
                    )
            )
        }
        else{
            Text(
                " ",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(
                        Color.LightGray
                    )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.LightGray
                )
        ) {
            teamList.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    UserID.value = teamList[selectedIndex].id
                    expanded = false
                }) {
                    Text(text = "${s.firstName} ${s.lastName}")
                }
            }
        }
    }
}