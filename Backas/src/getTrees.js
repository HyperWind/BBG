const firebase = require('firebase');
const _ = require('lodash');

const config = {databaseURL: "https://kaunas-hackathon-2018.firebaseio.com"};
firebase.initializeApp(config);

const getTrees = async () => {
  const snapshot = await firebase.database().ref('/visit/zeldiniai/').once('value');
  const json = snapshot.val();

  const filteredJson = json.map( tree => _.get(tree, 'geometry') );

  return filteredJson;
}

module.exports = {getTrees};