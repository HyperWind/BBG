// call the packages we need
const express    = require('express');        // call express
const app        = express();                 // define our app using express
const bodyParser = require('body-parser');


const routes =  require('./src/routes');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// const port = process.env.PORT || 8080;
const port = 8080;

var router = express.Router();             
routes(router);

// login
// trees
// trees, not-hugged
// user coupon inventory
// leaderboard
// goals
// treehugged -> cheque
// treeinfo
// couponinfo
// userinfo: taskai
//? business

app.use('/api', router);

app.listen(port);
console.log('Magic happens on port ' + port);
