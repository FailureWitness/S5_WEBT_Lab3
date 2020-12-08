// getPoints
// clearPoints
// addPoint

const canvasWrapper = document.getElementById('canvas-wrapper');
const canvas = document.getElementById('draw-field');

const context = canvas.getContext('2d');
context.fillStyle = '#000000';

let mousePressed = false;
let mouseInside = false;

let drawCircle = function(x, y, r){
	context.beginPath();
	context.arc(x, y, r, 0, 2 * Math.PI, true);
	context.fill();
}

let draw = function(){
	context.clearRect(0, 0, canvas.width, canvas.height)
	for(i in getPoints()){
		drawCircle(getPoints()[i].x, getPoints()[i].y, 1);
	}
}

if(getPoints === undefined || addPoint === undefined || clearPoints === undefined){
	var arr = new Array();
	var getPoints = function(){
		return arr;
	}
	var addPoint = function(x, y){
		if(arr.find(e => {return e.x === x && e.y === y}) === undefined){
			arr[arr.length] = {x: x, y: y};
		}
	}
	var clearPoints = function(){
		arr = new Array();
	}
}

window.onresize = e => {
	canvas.width = canvasWrapper.offsetWidth;
	canvas.height = canvasWrapper.offsetHeight-5;
	draw();
};
window.onresize(null);

window.addEventListener('keydown', e => {
	if(e.key === "Delete"){
		clearPoints();
	}
});

canvas.addEventListener('mouseenter', e => {
	mouseInside = true;
})
let lastPoint = null;
canvas.addEventListener('mousemove', e => {
	if(mousePressed && mouseInside){
		if(lastPoint != null){
			count = Math.max(Math.abs(e.offsetX - lastPoint.x), Math.abs(e.offsetY - lastPoint.y));
			for(i = 0; i < count; i++){
				lambda = i / count;
				tmpX = Math.round(lambda * e.offsetX + (1 - lambda) * lastPoint.x);
				tmpY = Math.round(lambda * e.offsetY + (1 - lambda) * lastPoint.y);
				addPoint(tmpX, tmpY);
			}
		}
		addPoint(e.offsetX, e.offsetY);
		lastPoint = {x: e.offsetX, y: e.offsetY};
		draw();
	}
})

canvas.addEventListener('mouseleave', e => {
	latPoint = null;
	mouseInside = false;
	mousePressed = false;
})

canvas.addEventListener('mousedown', e => {
	mousePressed = true;
	addPoint(e.offsetX, e.offsetY);
	lastPoint = {x: e.offsetX, y: e.offsetY};
	draw();
})
canvas.addEventListener('mouseup', e => {
	lastPoint = null;
	mousePressed = false;
})

updateInterval = setInterval(draw, 500)

// Made by EugeneVV