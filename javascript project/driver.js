

var Store = require('./Store'); 
var Parser = require('./Parser');



const store = new Store();
var parser = new Parser(process.argv[2], store); // you need to do test/whatever file you want

parser.parse('\"'+ process.argv[3] + '\"');


console.log(store.toString());