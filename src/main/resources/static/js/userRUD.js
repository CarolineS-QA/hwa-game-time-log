let readDisplay = document.querySelector("#readYourUserResponse");
let updateDisplay = document.querySelector("#updateYourUserResponse");
let deleteDisplay = document.querySelector("#deleteUserResponse");
let readUserbutt = document.querySelector("#bSubmitUsername");
let updateUserbutt = document.querySelector("#bSubmitUpdate");
let deleteUserbutt = document.querySelector("#bSubmitDelete");
const REQ = new XMLHttpRequest();

function getYourUser() {
    let username = document.getElementById("username").value;
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            buildUserDisplay(readDisplay, REQ.response);
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

readUserbutt.addEventListener('click', function (event) {
    event.preventDefault();
    getYourUser();
})

function updateUser() {
    let userId = document.getElementById("userIdUpdate").value;
    let freeTimeHours = document.getElementById("freeTimeHours").valueAsNumber;
    let freeTimeMinutes = document.getElementById("freeTimeMinutes").valueAsNumber;

    // if(freeTimeHours.value.length === 0){
    //     freeTimeHours = 0;
    // } else if(freeTimeMinutes.value === undefined ){
    //     freeTimeMinutes = 0;
    // }

    let freeTime = Number(freeTimeMinutes) + (Number(freeTimeHours) * 60);

    let jsonString = JSON.stringify(
        {
            //"username": username,
            "totalTimePlayed": 90001, //should be calculated based off of game sessions
            "freeTime": freeTime, // if no input then null is returned
            "timeRemaining": 1 //should be calculated based off of game sessions
        });

    REQ.open('PUT', `/updateUser/${userId}`);
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ.response);
            console.log("The data has been sent.");
            console.log(jsonString);
            window.alert("Your User has been updated!");
            messageDisplay(updateDisplay);
            window.location.reload();
        } else {
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
            console.log(REQ.status);
            console.log(freeTime);
            console.log(freeTime.data_type);
            console.log("You tried to send the following JSON");
            console.log(jsonString);
        }
    }
    REQ.send(jsonString);
}

updateUserbutt.addEventListener('click', function (event) {
    event.preventDefault();
    updateUser();
})

function deleteUser() {
    let userId = document.getElementById("userIdDelete").value;
    REQ.onload = () => {
        if (REQ.status === 204 && REQ.readyState === 4) {
            messageDisplay(deleteDisplay);
            console.log("The request to delete data has been sent.");
            window.alert("Your user has been successfully deleted. Thanks for using Game Time Log!");
            window.location.reload();
        } else {
            console.log(REQ);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
            console.log(REQ.status);
        }
    }
    REQ.open('DELETE', `/deleteUser/${userId}`);
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.send();
}

deleteUserbutt.addEventListener('click', function (event) {
    event.preventDefault();
    deleteUser();
})

function messageDisplay(messageDisplay){
    if (messageDisplay === updateDisplay){
        const updateMessage = document.createElement('h4');
        updateMessage.textContent = `Your user has been updated.`
        messageDisplay.appendChild(updateMessage);
    } else if (messageDisplay === deleteDisplay){
        const deleteMessage = document.createElement('h4');
        deleteMessage.textContent = `You have deleted your user. Thanks for using GameTimeLog!`
        messageDisplay.appendChild(deleteMessage);
    }
}

// container contains all the users
const container = document.createElement(`div`)
container.setAttribute('class', 'container')

//for getAllUsers.js
//data.forEach(user => {
// buildUserDisplay(display, user)
// }

function buildUserDisplay(placeholder, user){
        const card = document.createElement('div');
        card.setAttribute('user', 'card');

        const h4 = document.createElement('h4');
        h4.textContent = user.username;

        const userIdText = document.createElement('p');
        userIdText.textContent = `User Id: ${user.userId}`;

        const totalTimePlayedText = document.createElement('p');
        totalTimePlayedText.textContent = `Total Time Played (mins): ${user.totalTimePlayed}`;

        const freeTimeText = document.createElement('p');
        freeTimeText.textContent = `Free time for games (mins): ${user.freeTime}`;

        const timeRemainingText = document.createElement('p');
        timeRemainingText.textContent = `Time left available to play games (mins): ${user.timeRemaining}`;

        const sessionListText = document.createElement('p');
        sessionListText.textContent = `Games played: `;
        if (user.gameSessions.length === 0){
            sessionListText.textContent += `None`;
        }
        console.log(user.gameSessions);
        user.gameSessions.forEach(session =>{
            sessionListText.textContent += `${session.gameName}, `
        });
        sessionListText.textContent += `...that's it!`

        placeholder.appendChild(container)
        container.appendChild(card);
        card.appendChild(h4);
        card.appendChild(userIdText);
        card.appendChild(totalTimePlayedText);
        card.appendChild(freeTimeText);
        card.appendChild(timeRemainingText);
        card.appendChild(sessionListText);
    }
