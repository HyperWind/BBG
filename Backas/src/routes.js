const getTrees = require('./controller').getTrees;

module.exports = api => {
  api.get('/trees', async function(req, res, next) {
    try{
      const medziai = await getTrees();
      res.json(medziai);   
    } catch(e) {
      next(e);
    }
  });
  api.get('/tree/:json', async function(req, res, next) {
     try {
      const tree_ob = await getTree(req.params.json);
      res.json(tree_ob);
    } catch(e) {
      next(e);
    }
  });
  api.get('/login', function(req, res) {
    res.json({
      username: "Sveiki"
    });
  });
}
