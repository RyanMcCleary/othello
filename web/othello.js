const boardSize = 8;
const htmlBoard = document.getElementById('board');
let board = new Array(boardSize);
for (var i = 0; i<boardSize; i++) {
    board[i] = new Array(boardSize);
}
const SquareState = {
    EMPTY: 0,
    BLACK: 1,
    WHITE: 2
}
for (let i = 0; i < boardSize; i++) {
    board[i] = [];
    const row = htmlBoard.insertRow();
    for (let j = 0; j < boardSize; j++) {
        const cell = row.insertCell();
        cell.setAttribute("tabIndex", "0");
        cell.addEventListener('keypress', function(e) {
        if (e.key === "Enter" || e.key === "Spacebar") {
            cell.click();
        }});
        if ((i === 3 && j === 3) || (i === 4 && j === 4)) {
            cell.innerHTML=`<svg role="img" height="100" width="100" title="white">
            <desc>white</desc>
            <circle cx="50" cy="50" r="45" stroke="white" stroke-width="3" fill="white" />
            </svg>`;
            cell.style.backgroundColor = 'green';
            board[i][j] = SquareState.WHITE;
}
            else 
            if ((i === 3 && j === 4) || (i === 4 && j === 3)) {
                cell.innerHTML=`<svg role="img" height="100" width="100" title="black">
                <desc>black</desc>
                <circle cx="50" cy="50" r="45" stroke="black" stroke-width="3" fill="black" />
                </svg>`;
                cell.style.backgroundColor = 'green';
                board[i][j] = SquareState.BLACK;
            }   
            else {
                cell.onclick = () => play(cell);
            }
        }
}

let currentPlayer = 'black';
document.getElementById("currentplayer").innerHTML = "Black to Go";

function play(cell) {
    if (cell.title === "black" || cell.title === "white") {
        return;
    }
    
    cell.innerHTML=`<svg role="img" height="100" width="100" title="${currentPlayer}">
    <desc>${currentPlayer}</desc>
    <circle cx="50" cy="50" r="45" stroke="black" stroke-width="3" fill=${currentPlayer} />
    </svg>`;
    cell.setAttribute('title', currentPlayer);
    cell.style.backgroundColor = 'green';
    if (currentPlayer === 'black') {
        currentPlayer = 'white';
        document.getElementById("currentplayer").innerHTML = "White to Go";
    }
    else {
        currentPlayer = 'black';
        document.getElementById("currentplayer").innerHTML = "Black to Go";
    }
}

    
