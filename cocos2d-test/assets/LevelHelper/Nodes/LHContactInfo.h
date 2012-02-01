//  This file was generated by LevelHelper
//  http://www.levelhelper.org
//
//  LevelHelperLoader.h
//  Created by Bogdan Vladu
//  Copyright 2011 Bogdan Vladu. All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////
//
//  This software is provided 'as-is', without any express or implied
//  warranty.  In no event will the authors be held liable for any damages
//  arising from the use of this software.
//  Permission is granted to anyone to use this software for any purpose,
//  including commercial applications, and to alter it and redistribute it
//  freely, subject to the following restrictions:
//  The origin of this software must not be misrepresented; you must not
//  claim that you wrote the original software. If you use this software
//  in a product, an acknowledgment in the product documentation would be
//  appreciated but is not required.
//  Altered source versions must be plainly marked as such, and must not be
//  misrepresented as being the original software.
//  This notice may not be removed or altered from any source distribution.
//  By "software" the author refers to this code file and not the application 
//  that was used to generate this file.
//
////////////////////////////////////////////////////////////////////////////////

#import <Foundation/Foundation.h>
#import "cocos2d.h"
#include "Box2D.h"
#import "LHSprite.h"

enum LH_CONTACT_TYPE
{
    LH_BEGIN_CONTACT = 1,
    LH_END_CONTACT = 0,
    LH_PRE_SOLVE_CONTACT = -1,
    LH_POST_SOLVE_CONTACT = -2
};

@interface LHContactInfo : NSObject
{
    b2Body* bodyA; //week ptr;
    b2Body* bodyB; //week ptr;
    int contactType;
    b2Contact* contact; //available at both pre and post solve
    const b2Manifold* oldManifold;//available at pre solve - else is nil
    const b2ContactImpulse* impulse; //available at post solve - else is nil
}

-(id) initWithInfo:(b2Body*)bodyA 
             bodyB:(b2Body*)bodyB
           contact:(b2Contact*)_contact
       contactType:(int)type
          manifold:(const b2Manifold*)_manifold
           impulse:(const b2ContactImpulse*)_impulse;

+(id) contactInfoWithBodyA:(b2Body*)bodyA
                     bodyB:(b2Body*)bodyB
                   contact:(b2Contact*)_contact
               contactType:(int)type
                  manifold:(const b2Manifold*)_manifold
                   impulse:(const b2ContactImpulse*)_impulse;


-(b2Body*)bodyA;
-(b2Body*)bodyB;

//returns 
// 1 if its begin contact, 
// 0 if its end contact,
//-1 if its pre solve contact
//-2 if its post solve contact
-(enum LH_CONTACT_TYPE) contactType; 

-(LHSprite*)spriteA;//may return nil;
-(LHSprite*)spriteB;//may return nil;
-(b2Contact*)contact;//available at both pre and post solve
-(const b2Manifold*)oldManifold;//available at pre solve - else is nil
-(const b2ContactImpulse*)impulse;//available at post solve - else is nil
@end	