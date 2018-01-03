//
//  NotesTableViewController.swift
//  FireBaseDemo
//
//  Created by Sai Nishanth Dilly on 10/21/17.
//  Copyright © 2017 Sai Nishanth Dilly. All rights reserved.
//

import UIKit
import Firebase

class NotesTableViewController: UITableViewController, UserCellDelegate {
    
    
    var postTextField : UITextField?
    var posts = Array<Array<Post>>()
    var rootRef = Database.database().reference().child("Posts")
    var childRef = Database.database().reference().child("Posts").child((Auth.auth().currentUser?.uid)!)

    func deleteButtonPres(_ tag: IndexPath) {
        print(tag.row)
        print(tag.section)
        let postid = posts[tag.section][tag.row].id
        posts.removeAll()
        childRef.child(postid).removeValue()
        
    }
    
    
    
    func postTextField(textField: UITextField){
        postTextField = textField
        postTextField?.placeholder = "Note text"
    }

    
    @IBAction func backToBook(_ sender: UIBarButtonItem) {
        
        self.dismiss(animated: true, completion: nil)
    }
    
    
    func okhandler(aAction : UIAlertAction){
        
        print("post Added")
        
        if let x = postTextField?.text{
            let p = Post(postName : x)
            posts.append([p])
            posts.removeAll()
            childRef.child(p.id).updateChildValues(_: ["PostName":p.postName,"PostId":p.id
                , "PostDate":p.currDateTime])
            tableView.reloadData()
        }
        
    }

    
    @IBAction func addNewPost(_ sender: UIBarButtonItem) {
        
        let alert = UIAlertController(title: "New Note", message: "Enter New Post Text", preferredStyle: UIAlertControllerStyle.alert)
        alert.addTextField(configurationHandler: postTextField)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler: self.okhandler)
        let alertCancel = UIAlertAction(title:"Cancel", style: .cancel, handler: nil)
        alert.addAction(_ : alertAction)
        alert.addAction(_ : alertCancel)
        self.present(alert, animated: true, completion: nil)
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 500
        childRef.observe(.value) {[weak self] snapshot in
            for child in snapshot.children {
                let snap = child as! DataSnapshot
                let postDate = snap.children.allObjects[0] as! DataSnapshot
                let postId = snap.children.allObjects[1] as! DataSnapshot
                let postName = snap.children.allObjects[2] as! DataSnapshot
                let post = Post(postName:postName.value as! String)
                post.id = postId.value as! String
                post.currDateTime = postDate.value as! String
                self?.posts.append([post])
                
            }
            self?.tableView.reloadData()
            
        }

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return posts.count
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return posts[section].count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "postCell", for: indexPath) as! PostTableViewCell
        
        cell.postName.text = posts[indexPath.section][indexPath.row].postName
        cell.postCreated.text = "Created on " + posts[indexPath.section][indexPath.row].currDateTime
        cell.indexPath = indexPath
        cell.delegate = self

        // Configure the cell...

        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
