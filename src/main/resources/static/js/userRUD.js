let readDisplay = document.querySelector("#readYourUserResponse");
let updateDisplay = document.querySelector("#updateYourUserResponse");
let deleteDisplay = document.querySelector("#deleteUserResponse");
let readUserbutt = document.querySelector("#bSubmitUsername");
let updateUserbutt = document.querySelector("#bSubmitUpdate");
let deleteUserbutt = document.querySelector("#bSubmitDelete");
const container = document.createElement(`div`)
container.setAttribute('class', 'container')
let username = document.getElementById("username").value;
const REQ = new XMLHttpRequest();

function getYourUser() {

    REQ.onload = () => {
        if (REQ.status === 200 && REQ.readyState === 4) {
            console.log(REQ);
            console.log(REQ.response);
            buildDisplay(readDisplay, REQ.response);
            console.log("The request for data has been sent.");
        } else {
            console.log(REQ);
            console.log(REQ.response);
            console.log(`Oh no! You should handle the Error(s)!`);
            window.alert("Oops! Something went wrong...")
        }
    }
    REQ.open('GET', `/getUsersByUsername/${username}`);
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

    let freeTime = Number(freeTimeMinutes) + (Number(freeTimeHours) * 60);

    let jsonString = JSON.stringify(
        {
            "username": username,
            "totalTimePlayed": 90001, //should be calculated based off of game sessions
            "freeTime": freeTime,
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
            window.alert("Your User has been updated!")
            buildDisplay(updateDisplay, REQ.response);
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

deleteUserbutt.addEventListener('click', function (event) {
    event.preventDefault();
    deleteUser();
})

function buildDisplay(placeholder, data){
    data.forEach(user => {
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

        // const sessionListText = document.createElement('p');
        // console.log(user.gameSessions);
        // let gameSessionArray = user.gameSessions.toArray();
        // sessionListText.textContent = `Games played: ${gameSessionArray} ...`;

        placeholder.appendChild(container)
        container.appendChild(card);
        if (placeholder === updateDisplay){
            const updateMessage = document.createElement('h4');
            updateMessage.textContent = `Your new user details: `
            card.appendChild(updateMessage);
        } else if (placeholder === deleteDisplay){
            const deleteMessage = document.createElement('h4');
            deleteMessage.textContent = `You have deleted your user. Thanks for using GameTimeLog!`
            card.appendChild(deleteMessage);
        }
        card.appendChild(h4);
        card.appendChild(userIdText);
        //card.appendChild(sessionListText);
        card.appendChild(totalTimePlayedText);
        card.appendChild(freeTimeText);
        card.appendChild(timeRemainingText);
    })
}