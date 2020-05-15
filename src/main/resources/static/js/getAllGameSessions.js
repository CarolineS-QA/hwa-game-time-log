"use strict";

let displayAllGameSessions = document.querySelector("#getAllGameSessionsResponse");

const REQ = new XMLHttpRequest();

function getAllGameSessions() {
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            buildDisplayForGameSessions(displayAllGameSessions, REQ.response);
            console.log("The request for data has been sent.");
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.open('GET', '/getAllGameSessions');
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = 'json';
    REQ.send();
}

window.addEventListener('load', getAllGameSessions);

function buildDisplayForGameSessions(placeholder, data){
    const container = document.createElement('div')
    container.setAttribute('class', 'container')
    data.forEach(session => {
        const card = document.createElement('div');
        card.setAttribute('user', 'card');

        const h4GameName = document.createElement('h4');
        h4GameName.textContent = session.gameName;

        const idAndPlayerText = document.createElement('p');
        idAndPlayerText.textContent = `Session Id: ${session.sessionId}  //  Player: ${session.user}`;

        const timePlayedText = document.createElement('p');
        timePlayedText.textContent = `Time Played (mins): ${session.timePlayed}`;

        const timeOfSessionText = document.createElement('p');
        timeOfSessionText.textContent = `Time of session: ${session.timeOfSession}`;

        placeholder.appendChild(container)
        container.appendChild(card);
        card.appendChild(h4GameName);
        card.appendChild(idAndPlayerText);
        card.appendChild(timePlayedText);
        card.appendChild(timeOfSessionText);
    })
}