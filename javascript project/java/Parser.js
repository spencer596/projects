const fs = require('fs'); 
const readline = require('readline');
class Parser{
    constructor(filename, store){
        this.file = filename;
        this.store = store;
        this.numberOfFeaturesChecked = 0;
    }
    
    // something to do
    parse(tag){

        // basic file reader?

    const stream = fs.createReadStream(this.file);

    const rl = readline.createInterface({
        input: stream,
        crlfDelay: Infinity 
    })

    rl.on('line', (line) => {
        if (line.includes(tag)){
            // do something
            // somehow trim it so "user" isn't there
            // console.log(line);
           let str = line.replace(tag +':','');
            this.store.addToStore(str);
        }
    })
    }

    toString() {
        return "Feature Count: " + this.numberOfFeaturesChecked;
    }
}
module.exports = Parser;