class Store{
// some variable here idk

    constructor(){
        this.map = new Map();
    }

    addToStore(key){
        if (this.map.has(key)){
            let num = this.map.get(key);
            this.map.set(key,num + 1);

        } else {
            this.map.set(key,1);
        }


    }


    toString(){
        let sb = "";
    for (let [key, value] of this.map.entries()) {
        sb += key + ":" + value + "\n";
    }
    
    return sb;

    }



}
module.exports = Store;