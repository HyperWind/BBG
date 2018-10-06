const firebase = require('firebase');
const _ = require('lodash');

const config = {databaseURL: "https://kaunas-hackathon-2018.firebaseio.com"};
firebase.initializeApp(config);

const getDataFromTree = tree => {

  const trueFalse = [true, false];

  const filtered = 
  {
    latitude : _.get(tree, 'geometry.coordinates.0'),
    longitude : _.get(tree, 'geometry.coordinates.1'),
    treeType: _.get(tree, 'properties.Zeldinio_rusis'),
    id : _.get(tree, 'id'),
    hugged:  _.sample(trueFalse)
  }
  return filtered;
};

const getTrees = async () => {
  const snapshot = await firebase.database().ref('/visit/zeldiniai/').once('value');
  const json = snapshot.val().features;

  const filteredJson = json.map( tree =>  getDataFromTree(tree));

  return _.sampleSize(filteredJson, 20);
}

const getTree = async (res) => {
  const trees = getTrees();
  const mapped_params = res.split(",");

  return trees.map((tree) => {
    var newtree = {}

    mapped_params.forEach((param) => {
	newtree[param] = tree[param];
    });

    return newtree;
  });
}

module.exports = {getTrees};
