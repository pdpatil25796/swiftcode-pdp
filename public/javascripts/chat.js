var app = angular.module('chatApp',['ngMaterial']);

app.controller('chatController',function($scope){

	$scope.messages=[
	{
		sender:"BOT",
			text:"Hi ",
			time:"1:15pm"
	},
	{
		sender:"USER",
			text:"Hello",
			time:"1:15pm"
	},
	{
		sender:"BOT",
			text:"How are you?",
			time:"1:16pm"
	},
	{
		sender:"USER",
			text:"I am fine .How about you?",
			time:"1:16pm"
	}
		];

    var  exampleSocket =  new  WebSocket("ws://localhost:9000/chatSocket");
    exampleSocket.onmessage  =   function  (event) {
           var jsonData = JSON.parse(event.data);
           console.log(jsonData);
       };

})