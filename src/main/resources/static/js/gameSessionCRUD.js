"use strict";
const REQ = new XMLHttpRequest();

let submitCreateGameSession = document.querySelector("#bSubmitGameSession");

function postNewGameSession() {
    let user = REQ.response;
    // might only need userId and username...
    let gameName = document.getElementById("gameName").value;
    let timePlayedHours = document.getElementById("timePlayedHours").valueAsNumber;
    let timePlayedMinutes = document.getElementById("timePlayedMinutes").valueAsNumber;

    let timePlayed = Number(timePlayedMinutes) + (Number(timePlayedHours) * 60);
    let timeOfSession = "2007-12-03T10:15:30" //Date.now().toString();

    let jsonString = JSON.stringify(
        {
            "gameName": gameName,
            "user": user,
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

function getYourUser() {
    let username = document.getElementById("gamerId").value;
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ.response);
            postNewGameSession(REQ.response);
            console.log("The request for data has been sent.");
        } else if (REQ.status === 404) {
            window.alert("Your user wasn't found! Error 404.")
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.open('GET', `/getUserByUsername/${username}`);
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = 'json';
    REQ.send();
}

submitCreateGameSession.addEventListener('click', function (event) {
    event.preventDefault();
    getYourUser();
    //postNewGameSession();
})