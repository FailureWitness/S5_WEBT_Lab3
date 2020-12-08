//import SockJS from 'sockjs-client'
//import { Stomp } from '@stomp/stompjs'

let points = new Array();
let stompClient = null;
const clear = 'clear'

var addPoint;

fetch("/web-socket/arr").then(response => response.json()).then(data => points = data)

let connect = function(){
	const socket = new SockJS('/gs-guide-websocket')
	stompClient = Stomp.over(socket)
	stompClient.debug = (str) => {}
	stompClient.connect({}, frame => {
		stompClient.subscribe('/topic/points', message => {
			if(message.body === clear){
				points = new Array();
			} else {
				newPoints = JSON.parse(message.body);
				newPoints.forEach(e => {
					if(points.find(p => {return e.x === p.x && e.y === p.y}) === undefined){
						points.push(e);
					}
				})
			}
		})
	})
}

let disconnect = function(){
	if(stompClient !== null){
		stompClient.disconnect()
	}
}

let sendMessage = function(message){
	if(typeof message !== 'string' && !(message instanceof String)){
		message = JSON.stringify(message)
	}
	stompClient.send('/app/web-socket/message', {}, message)
}

let queue = new Array();
let sendInterval = setInterval(() => {
	if(queue.length != 0){
		let stack = new Array();
		for(i = 0; i < 1000 && queue.length != 0; i++)
			stack.push(queue.shift());
		sendMessage(stack);
	}
}, 100);

var getPoints = function(){
	return points;
}

addPoint = function(x, y) {
	if(points.find(e => {return e.x === x && e.y === y}) === undefined){
		points.push({x: x, y: y});
		queue.push({x: x, y: y});
	}
}

var clearPoints = function(){
	points = new Array();
	sendMessage(clear);
}

connect();

// Made by EugeneVV