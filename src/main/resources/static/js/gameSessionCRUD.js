"use strict";
const REQ = new XMLHttpRequest();

let submitCreateGameSession = document.querySelector("#bSubmitGameSession");

function postNewGameSession() {
    let gamerId = document.getElementById("gamerId").valueAsNumber;
    let gamerName = document.getElementById("gamerName").value;
    let gameName = document.getElementById("gameName").value;
    let timePlayedHours = document.getElementById("timePlayedHours").valueAsNumber;
    let timePlayedMinutes = document.getElementById("timePlayedMinutes").valueAsNumber;

    let timePlayed = Number(timePlayedMinutes) + (Number(timePlayedHours) * 60);
    let timeOfSession = "2007-12-03T10:15:30" //Date.now().toString();

    //only userId and username are needed for user, other values can be added but they don't affect the User
    let jsonString = JSON.stringify(
        {
            "gameName": gameName,
            "user": {
                "userId": gamerId,
                "username": gamerName
            },
            "timePlayed": timePlayed,
            "timeOfSession": timeOfSession,
        });

    REQ.open('POST', '/createGameSession');
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.onload = () => {
        if (REQ.status === 201 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            console.log("The data has been sent.");
            console.log(jsonString);
            window.alert("A Game Session has been created!");
            console.log(Date.now().toString())
            window.location.reload();
        } else if (REQ.status === 405) {
            window.alert("Bad request! Please check the data you are inputting.");
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
            console.log(`User ${user}`);
            console.log(`Time of Session is ${timeOfSession}`);
            console.log("You tried to send the following JSON");
            console.log(jsonString);
        }
    }
    REQ.send(jsonString);
}

submitCreateGameSession.addEventListener('click', function (event) {
    event.preventDefault();
    postNewGameSession();
})