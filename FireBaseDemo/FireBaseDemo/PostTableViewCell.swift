//
//  PostTableViewCell.swift
//  FireBaseDemo
//
//  Created by Sai Nishanth Dilly on 10/21/17.
//  Copyright © 2017 Sai Nishanth Dilly. All rights reserved.
//

import UIKit

protocol UserCellDelegate {
    
    func deleteButtonPres(_ tag: IndexPath)
}


class PostTableViewCell: UITableViewCell {
    
    @IBOutlet weak var postName: UILabel!
    
    @IBOutlet weak var postCreated: UILabel!
    
    var indexPath: IndexPath?
    
    var delegate : UserCellDelegate?
    
    
    
    @IBAction func deletePost(_ sender: UIButton) {
        
        print(indexPath!.row)
        print(indexPath!.section)
        delegate!.deleteButtonPres(indexPath!)
        
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
