const fs = require('fs'); 
const readline = require('readline');
class Parser{
    constructor(filename, store){
        this.file = filename;
        this.store = store;
        this.numberOfFeaturesChecked = 0;
    }


    parse(tag){
        const data = require("./" + this.file);
        if (!data.features || !Array.isArray(data.features)) {
            data.featureContainer.features.forEach(feature => {
                const str = feature.properties.user;
                this.numberOfFeaturesChecked++;
    
                this.store.addToStore(str);
            });
            return;
        }
        data.features.forEach(feature => {
            const str = feature.properties.user;
            this.numberOfFeaturesChecked++;

            this.store.addToStore(str);
        });
      
    }

    toString() {
        return "Feature Count: " + this.numberOfFeaturesChecked;
    }
}
module.exports = Parser;