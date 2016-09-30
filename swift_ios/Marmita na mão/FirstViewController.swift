//
//  FirstViewController.swift
//  Marmita na mão
//
//  Created by BRUNO  DE L H FERREIRA on 13/09/16.
//  Copyright © 2016 Softwareon. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {

    @IBOutlet weak var webView: UIWebView!
    @IBOutlet var Actind: UIActivityIndicatorView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        //let url = NSURL (string: "https://www.softwareon.com.br");
        //http://www.softwareon.com.br/marmita/
        
        let url = NSURL (string: "https://www.softwareon.com.br/marmita");
        
        NSURLCache.sharedURLCache().removeAllCachedResponses()
        NSURLCache.sharedURLCache().diskCapacity = 0
        NSURLCache.sharedURLCache().memoryCapacity = 0
        
        let requestObj = NSURLRequest(URL: url!);
        webView.loadRequest(requestObj);
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func webViewDidStartLoad(_ : UIWebView){
        Actind.stopAnimating();
    }
    
    func webViewDidFishLoad(_ : UIWebView){
        Actind.stopAnimating();
        
    }


}

