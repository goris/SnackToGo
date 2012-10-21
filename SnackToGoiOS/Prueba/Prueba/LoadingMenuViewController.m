//
//  LoadingMenuViewController.m
//  Prueba
//
//  Created by Compean on 10/21/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#define HOST @"10.20.98.87";
#define PORT 6666
#define ENABLE_BACKGROUNDING  0

#import "LoadingMenuViewController.h"
#import "SBJson.h"
#import "GCDAsyncSocket.h"
#import "MenuViewController.h"

@interface LoadingMenuViewController ()


@end

@implementation LoadingMenuViewController

GCDAsyncSocket *asyncSocket;
@synthesize jsonArray = _jsonArray;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    
	// Do any additional setup after loading the view.
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidLoad];
    _jsonArray = [[NSMutableArray alloc] init];
    dispatch_queue_t mainQueue = dispatch_get_main_queue();
    
    asyncSocket = [[GCDAsyncSocket alloc] initWithDelegate:self delegateQueue:mainQueue];
    NSString *host = HOST;
    uint16_t port = PORT;
    
    //DDLogInfo(@"Connecting to \"%@\" on port %hu...", host, port);
    //self.viewController.label.text = @"Connecting...";
    NSLog(@"Connecting to \"%@\" on port %hu...", host, port);
    NSLog(@"Connecting...");
    NSError *error = nil;
    
    if (![asyncSocket connectToHost:host onPort:port error:&error])
    {
        //DDLogError(@"Error connecting: %@", error);
        NSLog(@"Error connecting: %@", error);
        //self.viewController.label.text = @"Oops";
    } else {
        //NSMutableData *data;
        NSString *mensaje = @"articulos\n";
        //data = (NSMutableData *)[mensaje dataUsingEncoding:NSUTF8StringEncoding];
        //[data appendData:[GCDAsyncSocket CRLFData]];
        NSData *aqui = [@"\n" dataUsingEncoding:NSUTF8StringEncoding];
        [asyncSocket writeData:[mensaje dataUsingEncoding:NSUTF8StringEncoding] withTimeout:10 tag:1];
        //[asyncSocket readDataWithTimeout:20 tag:1];
        //[asyncSocket readDataWithTimeout:10 tag:1];
        //[asyncSocket readStream];
        [asyncSocket readDataToData:aqui withTimeout:20 tag:1];
        [asyncSocket disconnectAfterReadingAndWriting];
        //[asyncSocket ]
    }
}

- (void)socket:(GCDAsyncSocket *)sock didConnectToHost:(NSString *)host port:(UInt16)port
{
	//DDLogInfo(@"socket:%p didConnectToHost:%@ port:%hu", sock, host, port);
	//self.viewController.label.text = @"Connected";
	NSLog(@"Connected");
    //	DDLogInfo(@"localHost :%@ port:%hu", [sock localHost], [sock localPort]);
	
#if USE_SECURE_CONNECTION
	{
		// Connected to secure server (HTTPS)
        
#if ENABLE_BACKGROUNDING && !TARGET_IPHONE_SIMULATOR
		{
			// Backgrounding doesn't seem to be supported on the simulator yet
			
			[sock performBlock:^{
				if ([sock enableBackgroundingOnSocket])
					//DDLogInfo(@"Enabled backgrounding on socket");
                    else
                        //DDLogWarn(@"Enabling backgrounding failed!");
                        }];
		}
#endif
		
		// Configure SSL/TLS settings
		NSMutableDictionary *settings = [NSMutableDictionary dictionaryWithCapacity:3];
		
		// If you simply want to ensure that the remote host's certificate is valid,
		// then you can use an empty dictionary.
		
		// If you know the name of the remote host, then you should specify the name here.
		//
		// NOTE:
		// You should understand the security implications if you do not specify the peer name.
		// Please see the documentation for the startTLS method in GCDAsyncSocket.h for a full discussion.
		
		[settings setObject:@"10.20.98.87"
					 forKey:(NSString *)kCFStreamSSLPeerName];
		
		// To connect to a test server, with a self-signed certificate, use settings similar to this:
		
        //	// Allow expired certificates
        //	[settings setObject:[NSNumber numberWithBool:YES]
        //				 forKey:(NSString *)kCFStreamSSLAllowsExpiredCertificates];
        //
        //	// Allow self-signed certificates
        //	[settings setObject:[NSNumber numberWithBool:YES]
        //				 forKey:(NSString *)kCFStreamSSLAllowsAnyRoot];
        //
        //	// In fact, don't even validate the certificate chain
        //	[settings setObject:[NSNumber numberWithBool:NO]
        //				 forKey:(NSString *)kCFStreamSSLValidatesCertificateChain];
		
		DDLogInfo(@"Starting TLS with settings:\n%@", settings);
		NSLog(@"Starting TLS with settings:\n%@", settings);
		[sock startTLS:settings];
		
		// You can also pass nil to the startTLS method, which is the same as passing an empty dictionary.
		// Again, you should understand the security implications of doing so.
		// Please see the documentation for the startTLS method in GCDAsyncSocket.h for a full discussion.
		
	}
#else
	{
		// Connected to normal server (HTTP)
		
#if ENABLE_BACKGROUNDING && !TARGET_IPHONE_SIMULATOR
		{
			// Backgrounding doesn't seem to be supported on the simulator yet
			
			[sock performBlock:^{
				//if ([sock enableBackgroundingOnSocket])
                //DDLogInfo(@"Enabled backgrounding on socket");
				//else
                //DDLogWarn(@"Enabling backgrounding failed!");
			}];
		}
#endif
	}
#endif
}

- (void)socket:(GCDAsyncSocket *)sock didWriteDataWithTag:(long)tag
{
	//DDLogInfo(@"socket:%p didWriteDataWithTag:%ld", sock, tag);
    NSLog(@"socket:%p didWriteDataWithTag:%ld", sock, tag);
}

- (void)socket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag
{
	//DDLogInfo(@"socket:%p didReadData:withTag:%ld", sock, tag);
	NSLog(@"socket:%p didReadData:withTag:%ld", sock, tag);
	NSString *jsonResponse = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
	//jsonArray = [jsonResponse JSONValue];
    NSArray *jArr = [jsonResponse JSONValue];
    for (int i=0; i<jArr.count; i++) {
        [_jsonArray addObject:[jArr objectAtIndex:i]];
    }
    NSLog(@"json count: %d",[_jsonArray count]);
	//DDLogInfo(@"HTTP Response:\n%@", httpResponse);
    NSLog(@"JSON Response:\n%@", jsonResponse);
    //[self.view setNeedsDisplay];
    //[_tablaMenu setNeedsUpdateConstraints];
    //[_tablaMenu setNeedsDisplay];
    //[_tablaMenu setNeedsUpdateConstraints];
    //[_tablaMenu reloadData];
    
	
}

- (void)socketDidDisconnect:(GCDAsyncSocket *)sock withError:(NSError *)err
{
	//DDLogInfo(@"socketDidDisconnect:%p withError: %@", sock, err);
	//self.viewController.label.text = @"Disconnected";
    NSLog(@"Disconnected");
    NSLog(@"socketDidDisconnect:%p withError: %@", sock, err);
    //[[self navigationController] popViewControllerAnimated:YES];
    //[[UIViewController alloc] ini
    //[[self navigationController] pushViewController:<#(UIViewController *)#> animated:YES];
    //[self prepareForSegue:@"loadMenuSegue" sender:self];
    //[self performSegueWithIdentifier:@"loadMenuSegue" sender:self];
    //[[self navigationController] performSegueWithIdentifier:@"loadMenuSegue" sender:self];
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"MainStoryboard" bundle: nil];
    MenuViewController *lvc = [storyboard instantiateViewControllerWithIdentifier:@"MenuViewController"];
    [lvc setJsonArray:_jsonArray];
    [self.navigationController pushViewController:lvc animated:NO];
}




- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
