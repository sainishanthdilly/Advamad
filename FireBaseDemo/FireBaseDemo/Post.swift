//
//  Post.swift
//  FireBaseDemo
//
//  Created by Sai Nishanth Dilly on 10/21/17.
//  Copyright © 2017 Sai Nishanth Dilly. All rights reserved.
//

import Foundation

class Post{
    var id : String
    var currDateTime : String
    var postName : String
    init(postName: String){
        id = UUID().uuidString
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        let myString = formatter.string(from: Date() as Date)
        currDateTime = myString
        self.postName = postName
    }
    
}
