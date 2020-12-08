let points = new Array();
let lastUpdate = -2;
const clear = 'clear'

let sendMessage = function(msg){
	if(typeof msg !== 'string' && !(msg instanceof String)){
		msg = JSON.stringify(msg);
	}
	fetch("/long-poll/message",{
	    method: 'POST',
	    headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    body: msg
	})
}

let stopper = false
let updator = async function(){
	while(!stopper) {
//		await new Promise((a, b) => setTimeout(()=>{a('A')}, 1000)).then(d=>console.log(d))
		await fetch("/long-poll/arr?" + new URLSearchParams({lastUpdate: lastUpdate}))
		.then(data => data.text())
		.then(data => {
			if(data !== 'time out'){
				lastUpdate = Date.now();
				if(data === clear){
					points = new Array();
				} else {
					points = JSON.parse(data);
				}
			}
		})
	}
}
updator()

var getPoints = function(){
	return points;
}

let queue = new Array()
let sendInterval = setInterval(() => {
	if(queue.length != 0){
		let stack = new Array();
		for(i = 0; i < 1000 && queue.length != 0; i++)
			stack.push(queue.shift());
		sendMessage(stack);
	}
}, 100);

var addPoint = function(x, y) {
	if(points.find(e => {return e.x === x && e.y === y}) === undefined){
		points.push({x: x, y: y});
		queue.push({x: x, y: y});
	}
}

var clearPoints = function(){
	points = new Array();
	sendMessage(clear);
}

// Made by EugeneVV