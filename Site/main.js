var fireText = document.getElementById("fireText");

var firebaseTextRef = firebase.database().ref().child("Test");

firebaseTextRef.on('value', function(datasnapshot){
	fireText.innerText = datasnapshot.val();
	console.log(datasnapshot);
});