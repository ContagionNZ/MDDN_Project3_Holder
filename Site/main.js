var fireText = document.getElementById("fireText");
var fireTextTwo = document.getElementById("fireTextTwo");
var fireTextThree = document.getElementById("fireTextThree");

var firebaseTextRef = firebase.database().ref().child("Test one");

firebaseTextRef.on('value', function(datasnapshot){
	fireText.innerText = datasnapshot.val();
});

var firebaseTextRefTwo = firebase.database().ref().child("Test Two");

	firebaseTextRefTwo.on('value' , function(datasnapshot){
		fireTextTwo.innerText = datasnapshot.val();

	});

var firebaseTextRefThree = firebase.database().ref().child("Test Three");

	firebaseTextRefThree.on('value' , function(datasnapshot){
		fireTextThree.innerText = datasnapshot.val();

	});

	function doFunction(){

		firebase.database().ref("Test one").set('Order');
	}

	function doFunctionTwo(){

		firebase.database().ref("Test Two").set('Order');
	}

	function doFunctionThree(){

		firebase.database().ref("Test Three").set('Order');
	}