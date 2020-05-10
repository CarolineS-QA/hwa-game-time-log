let submitCreateUser = document.querySelector("#bSubmit");
const REQ = new XMLHttpRequest();

function postNewUser() {
    let username = document.getElementById("username").value;
    let freeTimeHours = document.getElementById("freeTimeHours").value;
    let freeTimeMinutes = document.getElementById("freeTimeMinutes").value;

    let freeTime = freeTimeMinutes + (freeTimeHours * 60);

    let jsonString = JSON.stringify(
        {
        "username": username,
        "totalTimePlayed": 0,
        "freeTime": freeTime,
        "timeRemaining": freeTime
        });

    REQ.open('POST', '/createUser');
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.onload = () => {
        if (REQ.status === 201 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            console.log("The data has been sent.");
            window.alert("A User has been created!")
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.send(jsonString);
}


submitCreateUser.addEventListener('click', function (event){
    event.preventDefault();
    postNewUser()
    let form = document.getElementById("userDetails")
    document.body.removeChild(form)
})