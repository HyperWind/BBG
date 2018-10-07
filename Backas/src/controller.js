const firebase = require('firebase');
const _ = require('lodash');
const Chance = require('chance');

const chance = Chance();

const config = {databaseURL: "https://kaunas-hackathon-2018.firebaseio.com"};
firebase.initializeApp(config);

const getDataFromTree = tree => {
  const filtered = 
  {
    latitude : _.get(tree, 'geometry.coordinates.0'),
    longitude : _.get(tree, 'geometry.coordinates.1'),
    treeType: _.get(tree, 'properties.Zeldinio_rusis'),
    id : _.get(tree, 'id'),
    // hugged:  chance.bool({ likelihood: 20 })
    hugged : false
  }
  return filtered;
};

const getTrees = async () => {
  const snapshot = await firebase.database().ref('/visit/zeldiniai/').once('value');
  const json = snapshot.val().features;

  const filteredJson = json.map( tree =>  getDataFromTree(tree));

  return _.sampleSize(filteredJson, 1000);
}

const getTree = async (params) => {
  const snapshot = await firebase.database().ref('/visit/zeldiniai/').once('value');
  const json = snapshot.val().features;

  const mapped_params = params.split(",");

  return json.map((tree) => {
    var newtree = {}

    mapped_params.forEach((param) => {
	    newtree[param] = tree[param];
    });

    return newtree;
  });
}

module.exports = {getTrees, getTree};
