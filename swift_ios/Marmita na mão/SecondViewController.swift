//
//  SecondViewController.swift
//  Marmita na mão
//
//  Created by BRUNO  DE L H FERREIRA on 13/09/16.
//  Copyright © 2016 Softwareon. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {


    @IBOutlet var loading: UIActivityIndicatorView!
    @IBOutlet var webView: UIWebView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let url = NSURL (string: "https://creator.ionic.io/share/080101d02850");
        
        
        //http://www.softwareon.com.br/marmita/
        
        //let url = NSURL (string: "https://getmdl.io/");
        
        NSURLCache.sharedURLCache().removeAllCachedResponses()
        NSURLCache.sharedURLCache().diskCapacity = 0
        NSURLCache.sharedURLCache().memoryCapacity = 0
        let requestObj = NSURLRequest(URL: url!);
        webView.loadRequest(requestObj);
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func webViewDidStartLoad(_ : UIWebView){
        loading.stopAnimating();
    }
    
    func webViewDidFishLoad(_ : UIWebView){
        loading.stopAnimating();
        
    }


}

