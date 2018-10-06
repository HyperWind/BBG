// call the packages we need
const express    = require('express');        // call express
const app        = express();                 // define our app using express
const bodyParser = require('body-parser');
const firebase =   require('firebase');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// const port = process.env.PORT || 8080;
const port = 3000;

const config = {databaseURL: "https://kaunas-hackathon-2018.firebaseio.com"};
firebase.initializeApp(config);

const getTrees = async () => {
  const snapshot = await firebase.database().ref('/visit/zeldiniai/').once('value');
  const json = snapshot.val();

  return json;
}

var router = express.Router();             

router.get('/', async function(req, res, next) {
  try{
    const medziai = await getTrees();
    res.json(medziai);   
  } catch(e) {
    next(e);
  }
});

app.use('/api', router);

app.listen(port);
console.log('Magic happens on port ' + port);
