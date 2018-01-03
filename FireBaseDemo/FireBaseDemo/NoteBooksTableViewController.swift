//
//  NoteBooksTableViewController.swift
//  FireBaseDemo
//
//  Created by Sai Nishanth Dilly on 10/20/17.
//  Copyright © 2017 Sai Nishanth Dilly. All rights reserved.
//

import UIKit
import Firebase
class NoteBooksTableViewController: UITableViewController {
    
    
    var noteTextField: UITextField?
    var notes = Array<Array<NoteBook>>()
    var curuser = (Auth.auth().currentUser?.uid)!
    var rootRef = Database.database().reference().child("Notes")
    var childRef = Database.database().reference().child("Notes").child((Auth.auth().currentUser?.uid)!)

    

    
    override func viewDidLoad() {
        super.viewDidLoad()
        childRef.observe(.value) {[weak self] snapshot in
            for child in snapshot.children {
                let snap = child as! DataSnapshot
                let noteBookDate = snap.children.allObjects[0] as! DataSnapshot
                let noteBookId = snap.children.allObjects[1] as! DataSnapshot
                let noteBookName = snap.children.allObjects[2] as! DataSnapshot
                let note = NoteBook(noteBookName:noteBookName.value as! String)
                note.id = noteBookId.value as! String
                note.currDateTime = noteBookDate.value as! String
                self?.notes.append([note])
                
            }
                self?.tableView.reloadData()
        
        }
       
        
       // childRef = rootRef.child(curuser!)
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    func okhandler(aAction : UIAlertAction){
        
        print("NoteBook Added")
        
        if let x = noteTextField?.text{
        let n = NoteBook(noteBookName : x)
        notes.append([n])
        notes.removeAll()
        childRef.child(n.id).updateChildValues(_: ["NoteBookName":n.noteBookName,"NoteBookId":n.id
                , "NoteBookDate":n.currDateTime])
        tableView.reloadData()
        }
    
    }
    @IBAction func addNote(_ sender: UIBarButtonItem) {
        
        let alert = UIAlertController(title: "New Notebook", message: "Enter Notebook Name", preferredStyle: UIAlertControllerStyle.alert)
        alert.addTextField(configurationHandler: noteTextField)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler: self.okhandler)
        let alertCancel = UIAlertAction(title:"Cancel", style: .cancel, handler: nil)
        alert.addAction(_ : alertAction)
        alert.addAction(_ : alertCancel)
        self.present(alert, animated: true, completion: nil)
    }
    
    func noteTextField(textField: UITextField){
        noteTextField = textField
        noteTextField?.placeholder = "Notebook Name"
    }
    
    @IBAction func onLogout(_ sender: UIBarButtonItem) {
        do{
        try Auth.auth().signOut()
        self.dismiss(animated: true, completion: nil)
            
        }
        catch{
            
            print("Problems Signing out")
        }
          }
    

    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return notes.count
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return notes[section].count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "noteCell", for: indexPath)

        // Configure the cell...
        cell.textLabel?.text = notes[indexPath.section][indexPath.row].noteBookName
        cell.detailTextLabel?.text = "Created On "+notes[indexPath.section][indexPath.row].currDateTime
        cell.accessibilityHint = notes[indexPath.section][indexPath.row].id
        
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

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        let cell = sender as! UITableViewCell
        
        //second approach
        //let i = tableView.indexPath(for:cell)!.row
       // print(i)
    
        if let x = segue.destination as? UINavigationController{
            if let c = x.visibleViewController as? NotesTableViewController{
                let ref = Database.database().reference().child("Posts").child((Auth.auth().currentUser?.uid)!)
                .child(cell.accessibilityHint!)
                c.childRef = ref
            
        }
    }
    }
   /* override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
       
        self.performSegue(withIdentifier: "postSegue", sender: notes[indexPath.section][indexPath.row])
    }
*/

}
