

var Store = require('./Store'); 
var Parser = require('./Parser');



var store = new Store();
var parser = new Parser(process.argv[2], store); // you need to do test-cases/whatever file you want

parser.parse('\"'+ process.argv[3] + '\"');

// store.map.get('_caleb:1');

console.log(store.toString());