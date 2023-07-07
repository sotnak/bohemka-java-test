db = new Mongo().getDB("db");

db.createCollection('item');

// expire document
db.item.createIndex( { "expireAt": 1 }, { expireAfterSeconds: 1 } );

// indexes for search
db.item.createIndex({"tag": 1});
db.item.createIndex({"value": 1});
db.item.createIndex({"updatedAt": 1});
