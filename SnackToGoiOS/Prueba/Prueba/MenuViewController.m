//
//  MenuViewController.m
//  Prueba
//
//  Created by Compean on 10/20/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#define HOST @"10.20.98.87";
#define PORT 6666
#define ENABLE_BACKGROUNDING  0

#import "GCDAsyncSocket.h"
#import "MenuViewController.h"

@interface MenuViewController ()

@end

@implementation MenuViewController

GCDAsyncSocket *asyncSocket;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
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
        //asyncSocket disconnectAfterReadingAndWriting];
        //[asyncSocket ]
    }
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
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
	
	//DDLogInfo(@"HTTP Response:\n%@", httpResponse);
    NSLog(@"JSON Response:\n%@", jsonResponse);
	
}

- (void)socketDidDisconnect:(GCDAsyncSocket *)sock withError:(NSError *)err
{
	//DDLogInfo(@"socketDidDisconnect:%p withError: %@", sock, err);
	//self.viewController.label.text = @"Disconnected";
    NSLog(@"Disconnected");
    NSLog(@"socketDidDisconnect:%p withError: %@", sock, err);
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
#warning Potentially incomplete method implementation.
    // Return the number of sections.
    return 0;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
#warning Incomplete method implementation.
    // Return the number of rows in the section.
    return 0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    // Configure the cell...
    
    return cell;
}

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Navigation logic may go here. Create and push another view controller.
    /*
     <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:@"<#Nib name#>" bundle:nil];
     // ...
     // Pass the selected object to the new view controller.
     [self.navigationController pushViewController:detailViewController animated:YES];
     */
}

@end
