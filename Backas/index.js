// call the packages we need
const express    = require('express');        // call express
const app        = express();                 // define our app using express
const bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// const port = process.env.PORT || 8080;
const port = 3000;

var router = express.Router();             

router.get('/', function(req, res) {
    res.json({ message: 'hooray! welcome to our api!' });   
});

app.use('/api', router);

app.listen(port);
console.log('Magic happens on port ' + port);
