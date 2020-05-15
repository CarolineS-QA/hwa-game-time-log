"use strict";
const REQ = new XMLHttpRequest();

let submitCreateGameSession = document.querySelector("#bSubmitGameSession");
let submitReadGameSession = document.querySelector("#bSubmitUsername");
let readSessionsDisplay = document.querySelector("#readYourGameSessionResponse");
let createSessionsDisplay = document.querySelector("#createdGameSessionResponse");
let updateSessionDisplay = document.querySelector("#updateYourGameSessionResponse");
let deleteSessionDisplay = document.querySelector("#deleteGameSessionResponse");


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

function getYourGameSession() {
    let usernameForGameSessions = document.getElementById("username").value;
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            buildUserSessionsDisplay(readSessionsDisplay, REQ.response);
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
    REQ.open('GET', `/getYourGameSessions/${usernameForGameSessions}`);
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = 'json';
    REQ.send();
}

submitReadGameSession.addEventListener('click', function (event) {
    event.preventDefault();
    getYourGameSession();
})

function buildUserSessionsDisplay(placeholder, data){
    const h4PlayerName = document.createElement('h4');
    h4PlayerName.textContent = `${data[0].user}'s game sessions`;

    const container = document.createElement('div')
    container.setAttribute('class', 'container')
    placeholder.appendChild(container);
    container.appendChild(h4PlayerName);

    data.forEach(session => {
        const card = document.createElement('div');
        card.setAttribute('session', 'card');

        const idAndGameText = document.createElement('p');
        idAndGameText.textContent = `Game: ${session.gameName}     //    Session Id: ${session.sessionId}`;

        const timePlayedText = document.createElement('p');
        timePlayedText.textContent = `Time Played (mins): ${session.timePlayed}`;

        const timeOfSessionText = document.createElement('p');
        timeOfSessionText.textContent = `Time of session: ${session.timeOfSession}`;

        container.appendChild(card);
        card.appendChild(idAndGameText);
        card.appendChild(timePlayedText);
        card.appendChild(timeOfSessionText);
    })
}

