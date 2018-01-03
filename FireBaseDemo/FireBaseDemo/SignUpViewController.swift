//
//  SignUpViewController.swift
//  FireBaseDemo
//
//  Created by Sai Nishanth Dilly on 10/19/17.
//  Copyright © 2017 Sai Nishanth Dilly. All rights reserved.
//

import UIKit
import Firebase

class SignUpViewController: UIViewController {

    
    
    @IBOutlet weak var regName: UITextField!
    
    @IBOutlet weak var regEmail: UITextField!
    
    @IBOutlet weak var regPass: UITextField!
    
    @IBOutlet weak var regConfPass: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    @IBAction func cancelR(_ sender: UIButton) {
        
        self.dismiss(animated: true, completion: nil)
    }
    
    func handleReg(){
        
        print( "UserCreated Successfully")
        let changeRequest = Auth.auth().currentUser?.createProfileChangeRequest()
        changeRequest?.displayName = self.regName.text
        changeRequest?.commitChanges { (error) in
            
            
        }
        self.performSegue(withIdentifier: "registerCompleteSegue", sender: self)
        
            
        
    }
    
    
    
    @IBAction func register(_ sender: UIButton) {
        
        if !(regEmail.text == "" || regPass.text == "" || regConfPass.text == "" || regName.text  == ""){
            
            if(regConfPass.text == regPass.text){
            
            let email = regEmail.text!
            let password = regPass.text!
                Auth.auth().createUser(withEmail: email, password: password) {[weak self] (user, error) in
                 
                    if user != nil {
                        DispatchQueue.main.async {
                            
                         self?.handleReg()
                        
                        }
                    }
                    else{
                        
                        DispatchQueue.main.async {
                            let alert = UIAlertController(title: "Unable to create user", message: error?.localizedDescription, preferredStyle: UIAlertControllerStyle.alert)
                            alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                            self?.present(alert, animated: true, completion: nil)
                            
                        }
                        
                    }
                    
                }
            
            }
            else{
                let alert = UIAlertController(title: "Password Mismatch", message: "Enter same password and confirm password", preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                self.present(alert, animated: true, completion: nil)
                
            }
        
    }
    else{
            
            let alert = UIAlertController(title: "Invalid Input", message: "Enter non empty fields", preferredStyle: UIAlertControllerStyle.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
            
            
    }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
